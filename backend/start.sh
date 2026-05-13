#!/bin/bash

# Default port
PORT=${PORT:-5000}

# Wait for MongoDB to be ready (if running in Docker)
if [ ! -z "$MONGODB_URI" ]; then
  echo "Waiting for MongoDB to be ready..."
  until nc -z $(echo $MONGODB_URI | sed -n 's/.*\/\/\([^:]*\).*/\1/p') 27017; do
    sleep 1
  done
  echo "MongoDB is ready!"
fi

# Start the application
node server.js
