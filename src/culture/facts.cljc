(ns culture.facts
  "Regional-culture catalog for Cairo -- local dishes, protected
  products, beverages, festivals and heritage sites, piggybacked
  onto this municipality compliance repo per ADR-2607171400
  (cloud-itonami-municipality-culture-catalog, in com-junkawasaki/root),
  sibling namespace to `ordinance.facts` (ADR-2607141700).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "municipality-slug -> vector of culture entries."
  {"cairo"
   [{:culture/id "cairo.dish.kushari"
     :culture/name "Kushari"
     :culture/name-local "كشري"
     :culture/municipality "cairo"
     :culture/country "EGY"
     :culture/kind :dish
     :culture/summary "Mixed dish of pasta, rice, lentils and vermicelli topped with chickpeas and tomato sauce; Egypt's national dish and a widely popular street food."
     :culture/url "https://en.wikipedia.org/wiki/Kushari"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cairo.dish.ful-medames"
     :culture/name "Ful medames"
     :culture/name-local "فول مدمس"
     :culture/municipality "cairo"
     :culture/country "EGY"
     :culture/kind :dish
     :culture/summary "Stew of cooked fava beans, a staple food in Egypt considered a national dish."
     :culture/url "https://en.wikipedia.org/wiki/Ful_medames"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cairo.dish.mulukhiyah"
     :culture/name "Mulukhiyah"
     :culture/name-local "ملوخية"
     :culture/municipality "cairo"
     :culture/country "EGY"
     :culture/kind :dish
     :culture/summary "Leafy-vegetable dish made from jute leaves that originated in ancient Egypt, considered by many Egyptians a national dish alongside ful medames and kushari."
     :culture/url "https://en.wikipedia.org/wiki/Mulukhiyah"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cairo.dish.hawawshi"
     :culture/name "Hawawshi"
     :culture/name-local "حواوشي"
     :culture/municipality "cairo"
     :culture/country "EGY"
     :culture/kind :dish
     :culture/summary "Traditional Egyptian pita stuffed with spiced minced meat; one account credits its 1971 invention to a butcher in Cairo's Souk Al-Tawfik neighborhood."
     :culture/url "https://en.wikipedia.org/wiki/Hawawshi"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cairo.craft.khayamiya"
     :culture/name "Khayamiya"
     :culture/name-local "خيامية"
     :culture/municipality "cairo"
     :culture/country "EGY"
     :culture/kind :craft
     :culture/summary "Decorative Egyptian appliqué textile art made primarily in Cairo along the Street of the Tentmakers."
     :culture/url "https://en.wikipedia.org/wiki/Khayamiya"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cairo.beverage.karkadeh"
     :culture/name "Hibiscus tea"
     :culture/name-local "كركديه"
     :culture/municipality "cairo"
     :culture/country "EGY"
     :culture/kind :beverage
     :culture/summary "Infusion of roselle flower sepals sold by many vendors and open-air cafés on the streets of Cairo; wedding celebrations in Egypt are traditionally toasted with it."
     :culture/url "https://en.wikipedia.org/wiki/Hibiscus_tea"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cairo.festival.sham-ennessim"
     :culture/name "Sham Ennessim"
     :culture/name-local "شم النسيم"
     :culture/municipality "cairo"
     :culture/country "EGY"
     :culture/kind :festival
     :culture/summary "Egyptian spring festival celebrated on Easter Monday, spent outdoors picnicking with traditional foods such as colored eggs and fesikh."
     :culture/url "https://en.wikipedia.org/wiki/Sham_Ennessim"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cairo.heritage.islamic-cairo"
     :culture/name "Islamic Cairo"
     :culture/municipality "cairo"
     :culture/country "EGY"
     :culture/kind :heritage
     :culture/summary "Areas of Cairo built from the Muslim conquest of 641 CE through the 19th century, proclaimed the Historic Cairo UNESCO World Cultural Heritage site in 1979."
     :culture/url "https://en.wikipedia.org/wiki/Islamic_Cairo"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cairo.heritage.khan-el-khalili"
     :culture/name "Khan el-Khalili"
     :culture/name-local "خان الخليلي"
     :culture/municipality "cairo"
     :culture/country "EGY"
     :culture/kind :heritage
     :culture/summary "Famous bazaar in the historic center of Cairo, one of the city's main attractions."
     :culture/url "https://en.wikipedia.org/wiki/Khan_el-Khalili"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cairo.heritage.citadel-of-cairo"
     :culture/name "Citadel of Cairo"
     :culture/name-local "قلعة صلاح الدين"
     :culture/municipality "cairo"
     :culture/country "EGY"
     :culture/kind :heritage
     :culture/summary "Medieval Islamic-era fortification in Cairo begun by Saladin in 1176, seat of Egyptian government for nearly 700 years."
     :culture/url "https://en.wikipedia.org/wiki/Citadel_of_Cairo"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [muni] (get catalog muni))

(defn coverage
  ([] (coverage (keys catalog)))
  ([munis]
   (let [have (filter catalog munis)
         missing (remove catalog munis)]
     {:requested (count munis)
      :covered (count have)
      :covered-municipalities (vec (sort have))
      :missing-municipalities (vec (sort missing))
      :note (str "cloud-itonami-municipality-egy-cairo culture catalog "
                 "(ADR-2607171400): " (count (get catalog "cairo"))
                 " Cairo entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [muni kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis muni)))
