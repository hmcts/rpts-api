FROM postgres:11.2-alpine

HEALTHCHECK --interval=10s --timeout=10s --retries=10 CMD psql -c 'select 1' -d rpts -U rpts

# Install prerequisites
RUN apk --no-cache add curl

# Add NSPL 9 char data volume
RUN curl https://www.arcgis.com/sharing/rest/content/items/aef0a4ef0dfb49749fe4f80724477687/data -L -o /tmp/uk_data.zip \
  && unzip /tmp/uk_data.zip \
  && rm /tmp/uk_data.zip \
  && rm /Data/NSPL_AUG_2021_UK.txt \
  && rm -rf /Data/multi_csv

# Add 4 char volume equivalents
RUN curl https://www.arcgis.com/sharing/rest/content/items/8e8f12e8fa5d476e829b105103ada83c/data -L -o /tmp/equivalents_data.zip \
  && unzip /tmp/equivalents_data.zip -d /Data \
  && rm /tmp/equivalents_data.zip \
  && rm -rf /Data/Documents

EXPOSE 5432
