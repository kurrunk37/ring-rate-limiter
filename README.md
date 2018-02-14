# ring-rate-limiter

Standard Ring middleware functions , Rate limiter.

## Installation

To install, add the following to your project `:dependencies`:

```clojure
[huzhengquan/ring-rate-limiter "0.1.0-SNAPSHOT"]
```

## Usage

```clojure
(require '[ring.middleware.rate-limiter :refer [wrap-rate-limiter]])

(defn handler [request]
  (response {:foo "bar"}))

(def app
  (wrap-rate-limiter
    handler
    :interval 60 ; required (s)
    :max-in-interval 3 ; required
    :key-in-request [:server-name] ; default [:remote-addr]
    :debug ; default false
    :fail-response ; default "Too Many Requests"
    ))
```
