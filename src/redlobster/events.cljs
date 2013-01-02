(ns redlobster.events)

(defn- detect-implementation []
  (try
    ;; Try Node's EventEmitter
    {:emitter (.-EventEmitter (js/require "events"))
     :type :node}
    (catch js/Error e
      (try
        ;; Try Ace's EventEmitter using RequireJS
        (let [ace-emitter (js/require "ace/lib/event")
              emitter (fn [])]
          (aset emitter "prototype" ace-emitter)
          {:emitter emitter
           :type :ace})
        (catch js/Error e
          (throw (js/Error. "No supported EventEmitter found")))))))

(let [emitter (detect-implementation)]
  (def EventEmitter (:emitter emitter))
  (def emitter-type (:type emitter)))

(defn event-emitter []
  (EventEmitter.))

(defprotocol IEventEmitter
  (on [emitter event listener])
  (once [emitter event listener])
  (remove-listener [emitter event listener])
  (remove-all-listeners [emitter])
  (remove-all-listeners [emitter event])
  (listeners [emitter event])
  (emit [emitter event data]))

(defn- unpack-event [event]
  (if (keyword? event)
    (name event)
    event))

(defn- wrap-once [emitter event listener]
  (fn once-off [x]
    (listener x)
    (remove-listener emitter event once-off)))

(case emitter-type
  :node
  (extend-protocol IEventEmitter
   EventEmitter
   (on [emitter event listener]
     (.on emitter (unpack-event event) listener))
   (once [emitter event listener]
     (.once emitter (unpack-event event) listener))
   (remove-listener [emitter event listener]
     (.removeListener emitter (unpack-event event) listener))
   (remove-all-listeners [emitter]
     (.removeAllListeners emitter))
   (remove-all-listeners [emitter event]
     (.removeAllListeners emitter (unpack-event event)))
   (listeners [emitter event]
     (js->clj (.listeners emitter (unpack-event event))))
   (emit [emitter event data]
     (.emit emitter (unpack-event event) data)))

  :ace
  (extend-protocol IEventEmitter
   EventEmitter
   (on [emitter event listener]
     (.on emitter (unpack-event event) listener))
   (once [emitter event listener]
     (let [event (unpack-event event)]
       (.on emitter event (wrap-once emitter event listener))))
   (remove-listener [emitter event listener]
     (.removeListener emitter (unpack-event event) listener))
   (remove-all-listeners [emitter]
     (throw "ace.lib.event_emitter.EventEmitter doesn't support the `remove-all-listeners` method without an event argument."))
   (remove-all-listeners [emitter event]
     (.removeAllListeners emitter (unpack-event event)))
   (listeners [emitter event]
     (throw "ace.lib.event_emitter.EventEmitter doesn't support the `once` method."))
  (emit [emitter event data]
    (._emit emitter (unpack-event event) data))))
