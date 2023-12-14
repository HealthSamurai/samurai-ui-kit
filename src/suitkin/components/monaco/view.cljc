(ns suitkin.components.monaco.view
  #?(:cljs (:require ["@monaco-editor/react" :default Editor])))

(defn component
  [properties]
  (fn [properties]
    [:> #?(:cljs Editor)
     (-> 
      {:theme    (:theme properties "suitkin-theme")
       :language "json"
       :options  {:minimap              {:enabled false}
                  :fontSize             "14px"
                  :fontStyle            "normal"
                  :lineHeight           "1.5"
                  :letterSpacing        "0.2em"
                  :fontWeight           "300"
                  :fontFamily           "JetBrains Mono"
                  :overviewRulerLanes   0
                  :lineNumbers          "off"
                  :glyphMargin          false
                  :renderLineHighlight  "none"
                  :folding              false
                  :renderIndentGuides   false
                  :lineDecorationsWidth 0
                  :tabSize              2
                  :lineNumbersMinChars  0}
       :beforeMount
       (fn [insance]
         ;;(reset! monaco-instance insance)
         #?(:cljs 
            (.defineTheme (.-editor ^js/Object insance)
                          "suitkin-theme"
                          (clj->js {:base    "vs"
                                    :inherit true
                                    :rules   [{:token "string.key.json" :foreground "#EA4A35"}
                                              {:token "string.value.json" :foreground "#405CBF"}
                                              {:token "string" :foreground "#405CBF"}
                                              {:token "number" :foreground "#00A984"}]
                                    :colors  {"editor.background" "#F8FAFC"
                                              "scrollbar.shadow"  "#ffffff00"
                                              "widget.shadow"     "#ffffff00"}}))
            :clj nil)
         insance)}
      (merge properties))]))
