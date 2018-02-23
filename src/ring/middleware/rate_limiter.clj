(ns ring.middleware.rate-limiter)

(def time-key (atom nil))
(def user-counter (atom {}))

(defn wrap-rate-limiter
  [handler & {:keys [interval max-in-interval key-in-request fail-response debug]
              :or {key-in-request [:remote-addr]
                   fail-response "Too Many Requests"
                   debug false}}]
  (fn [request]
    (if (and (number? interval) (number? max-in-interval))
      (let [now-key (-> (System/currentTimeMillis) (/ interval 1000) long)
            rl-key (get-in request key-in-request)]
        (when (not= now-key @time-key)
          (reset! user-counter {})
          (reset! time-key now-key))
        (if (contains? @user-counter rl-key)
          (swap! user-counter update rl-key inc)
          (swap! user-counter assoc rl-key 1))
        (when debug
          (println :rate-limiter :debug :rl-key rl-key :now-key now-key :count (get @user-counter rl-key)))
        (if (> max-in-interval (get @user-counter rl-key 0))
          (handler request)
          (do 
            (when debug (println :rate-limiter :debug :too-many-requests :rl-key rl-key))
            {:status 429 :body fail-response})))
      (handler request))))
