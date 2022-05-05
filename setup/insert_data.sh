# Run this script to populate local dev database with CSV information
# This will import over 2 million rows into one of the tables
# Note: Make sure you have ran the application before running this, and
# this is to be used primarily to refresh the database rows.
mkdir "./tmp";

curl "https://www.arcgis.com/sharing/rest/content/items/aef0a4ef0dfb49749fe4f80724477687/data" -L -o "./tmp/uk_data.zip" \
  && unzip "./tmp/uk_data.zip" \
  && curl "https://www.arcgis.com/sharing/rest/content/items/8e8f12e8fa5d476e829b105103ada83c/data" -L -o "./tmp/equivalents_data.zip" \
  && unzip "./tmp/equivalents_data.zip" -d "./Data" \
  && mv "./data/NSPL_AUG_2021_UK.csv" "./tmp" \
  && mv "./data/Equivalents.csv" "./tmp" \
  && rm -rf "./Data" \
  && rm -rf "./Documents" \
  && rm -rf "./User Guide" \
  && rm "./tmp/equivalents_data.zip" \
  && rm "./tmp/uk_data.zip";

echo -e "Enter password when prompted and please be patient :) as there are many rows that will be added.";

# Delete previous data (incase there are lingering rows based on a previous run)
psql -U rpts -h localhost -W -c "DELETE FROM nspl;DELETE FROM nspl_history;";

# Copy values from the spreadsheet to the two tables
echo -e "$(psql -U rpts -h localhost -W -c "\COPY public.nspl(pcd,
    pcd2,
    pcds,
    dointr,
    doterm,
    usertype,
    oseast1m,
    osnrth1m,
    osgrdind,
    oa11,
    cty,
    ced,
    laua,
    ward,
    hlthau,
    nhser,
    ctry,
    rgn,
    pcon,
    eer,
    teclec,
    ttwa,
    pct,
    itl,
    park,
    lsoa11,
    msoa11,
    wz11,
    ccg,
    bua11,
    buasd11,
    ru11ind,
    oac11,
    lat,
    long,
    lep1,
    lep2,
    pfa,
    imd,
    calncv,
    stp) FROM './tmp/NSPL_AUG_2021_UK.csv' CSV HEADER DELIMITER ',';")";

echo -e "$(psql -U rpts -h localhost -W -c "\COPY public.nspl_history(
    GEOGCD,
    GEOGNM,
    GEOGNMW,
    GEOGCDO,
    GEOGNMO,
    GEOGCDD,
    GEOGNMD,
    GEOGCDH,
    GEOGNMH,
    GEOGCDS,
    GEOGNMS,
    GEOGCDI,
    GEOGNMI,
    GEOGCDWG,
    GEOGNMWG,
    GEOGNMWWG,
    OPER_DATE,
    TERM_DATE,
    ENTITYCD,
    YEAR,
    STATUS) FROM './tmp/Equivalents.csv' CSV HEADER DELIMITER ',';")";

rm -rf "./tmp";
