(ns suitkin.components.input.view
  (:require
   [suitkin.components.input.styles :as s]))

(defn icon-wrapper
  [properties element]
  (let [left-section  (:s/left properties)
        right-section (:s/right properties)]
    (if (or left-section right-section)
      [:fieldset {:class s/icon-wrapper}
       (when left-section [:span.left left-section])
       element
       (when right-section [:span.right right-section])]
      element)))


(defn component
  [properties]
  (icon-wrapper
    properties
    [:input
     (merge
       {:type      (:type properties)
        :on-click  (:on-click properties)
        :on-change (:on-change properties)
        :class     [s/root (if (= "narrow" (:s/size properties)) s/narrow s/default) (:class properties)]}
       (dissoc properties :s/size :s/right :s/left))]))
