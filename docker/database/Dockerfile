FROM postgres:11.2-alpine

HEALTHCHECK --interval=10s --timeout=10s --retries=10 CMD psql -c 'select 1' -d javadatabase -U javapostgres

# Install prerequisites
RUN apk --no-cache add curl

# Add NSPL 9 char data volume
RUN curl https://www.arcgis.com/sharing/rest/content/items/9ac0331178b0435e839f62f41cc61c16/data -L -o /tmp/uk_data.zip \
  && unzip /tmp/uk_data.zip \
  && rm /tmp/uk_data.zip \
  && rm /Data/NSPL_MAY_2022_UK.txt \
  && rm -rf /Data/multi_csv

# Add 4 char volume equivalents
RUN curl https://www.arcgis.com/sharing/rest/content/items/98dc824887d4471cb9da378ffd030db5/data -L -o /tmp/equivalents_data.zip \
  && unzip /tmp/equivalents_data.zip -d /Data \
  && rm /tmp/equivalents_data.zip \
  && rm -rf /Data/Documents

EXPOSE 5432
