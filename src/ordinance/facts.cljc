(ns ordinance.facts
  "Municipal-ordinance compliance catalog for Cairo -- the
  THIRTY-SIXTH municipality-level entry (see cloud-itonami-municipality-jpn-tokyo,
  -usa-washington-dc, -gbr-london, -can-toronto, -deu-berlin, -fra-paris,
  -nld-amsterdam, -esp-madrid, -kor-seoul, -ita-roma, -aus-sydney,
  -arg-buenos-aires, -fin-helsinki, -dnk-copenhagen, -nor-oslo,
  -bel-brussels, -chl-santiago, -col-bogota, -cri-san-jose,
  -bra-sao-paulo, -ury-montevideo, -zaf-cape-town, -ecu-quito,
  -swe-gothenburg, -pry-asuncion, -mex-guadalajara, -fra-lyon,
  -ind-new-delhi, -pol-warsaw, -ken-nairobi, -tha-bangkok, -are-abu-dhabi,
  -vnm-hanoi, -idn-jakarta, -phl-manila for the first thirty-five) per
  ADR-2607141700 (cloud-itonami-compliance-fact-federation). The
  axis's first North African entry.

  Egypt's 'New Capital' (formerly New Administrative Capital,
  inaugurated as the seat of government in April 2024, with the
  Cabinet, House of Representatives, and Central Bank already
  relocated there) is undergoing an active, unresolved transition; as
  of this tick a February 2026 draft law proposing 'special status'
  for the New Capital (and a possible rename to 'Memphis') remains
  pending, and Wikidata's own P36 (capital) property for Egypt still
  lists Cairo -- this catalog follows that current Wikidata
  designation and documents the ongoing ambiguity transparently
  rather than guessing which way it will resolve, similar in spirit
  to this session's Indonesia Jakarta/Nusantara check.

  Law No. 43 of 1979 (Local Government System Law) -- title, law
  number, and 20 June 1979 issuance date directly confirmed via
  lawyeregypt.net (an Egyptian legal-information site, actually
  fetched and read, not merely cited from a failed-fetch WebSearch
  synthesis -- an ILO NATLEX attempt returned HTTP 403 first). Law
  No. 187 of 2023 (Building Violations Reconciliation Law) -- law
  number directly confirmed via blogs.realestate.gov.eg (an official
  Egyptian government domain, Ministry of Housing's Real Estate
  Platform); the exact day/month of issuance was not independently
  confirmed via a successfully-rendered page, so :enacted-date is
  year-only rather than guessed.

  An ordinance not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url/date.")

(def catalog
  "municipality-slug -> vector of ordinance entries."
  {"cairo"
   [{:ordinance/id "cairo.law-43-1979-local-government-system"
     :ordinance/title "Law No. 43 of 1979 for Issuing the Local Government System Law"
     :ordinance/municipality "cairo"
     :ordinance/country "EGY"
     :ordinance/kind :local-act
     :ordinance/number "Law No. 43 of 1979"
     :ordinance/url "https://lawyeregypt.net/%D9%82%D8%A7%D9%86%D9%88%D9%86-%D9%86%D8%B8%D8%A7%D9%85-%D8%A7%D9%84%D8%AD%D9%83%D9%85-%D8%A7%D9%84%D9%85%D8%AD%D9%84%D9%89-%D8%B1%D9%82%D9%85-43-%D9%84%D8%B3%D9%86%D8%A9-1979/"
     :ordinance/url-provenance :lawyeregypt-net-legal-database
     :ordinance/enacted-date "1979-06-20"
     :ordinance/retrieved-at "2026-07-16"
     :ordinance/topic #{:governance}}
    {:ordinance/id "cairo.law-187-2023-building-violations-reconciliation"
     :ordinance/title "Building Violations Reconciliation Law (Law No. 187 of 2023)"
     :ordinance/municipality "cairo"
     :ordinance/country "EGY"
     :ordinance/kind :local-act
     :ordinance/number "Law No. 187 of 2023"
     :ordinance/url "https://blogs.realestate.gov.eg/egypt-building-violations-reconciliation-law/"
     :ordinance/url-provenance :official-realestate-gov-eg
     :ordinance/enacted-date "2023"
     :ordinance/retrieved-at "2026-07-16"
     :ordinance/topic #{:construction}}]})

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
      :note (str "cloud-itonami-municipality-egy-cairo Wave 0 (ADR-2607141700): "
                 (count (get catalog "cairo")) " Cairo entries seeded "
                 "with lawyeregypt.net/realestate.gov.eg citations. "
                 "Extend `ordinance.facts/catalog`, never fabricate an id/url.")})))

(defn by-topic [muni topic]
  (filterv #(contains? (:ordinance/topic %) topic) (spec-basis muni)))
