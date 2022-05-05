DO $$
    BEGIN
        -- Required to run the copy command further down
        grant pg_read_server_files to rpts;
    EXCEPTION
              WHEN OTHERS THEN
            raise notice 'The grant has not been applied.';
            raise notice '% %', SQLERRM, SQLSTATE;
    END
$$;

DO $$
    BEGIN
        -- Copy the data to the temp tables
        COPY public.nspl(pcd,
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
            stp) FROM '/Data/NSPL_AUG_2021_UK.csv' CSV HEADER DELIMITER ',';

        COPY public.nspl_history(
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
            STATUS) FROM '/Data/Equivalents.csv' CSV HEADER DELIMITER ',';

        raise notice 'The data was copied successfully';

    EXCEPTION
          WHEN OTHERS THEN
        raise notice 'Skipping copy, or failure found.';
        raise notice '% %', SQLERRM, SQLSTATE;
    END
$$;
