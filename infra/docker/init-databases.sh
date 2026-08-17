#!/bin/bash
set -e

for db in nexus_user nexus_catalog nexus_pricing nexus_inventory nexus_order nexus_payment; do
  psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE DATABASE $db;
EOSQL
done
