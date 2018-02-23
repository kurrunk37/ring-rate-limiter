(defproject huzhengquan/ring-rate-limiter "0.1.2"
  :description "ring middleware, rate limiter"
  :url "https://github.com/huzhengquan/ring-rate-limiter"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :deploy-repositories [["releases" :clojars
                         :creds :gpg ]
                        ["snapshots" :clojars
                         :creds :gpg]]
  :aot :all)
