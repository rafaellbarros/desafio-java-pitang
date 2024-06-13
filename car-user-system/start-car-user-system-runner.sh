#!/bin/bash

echo "========== Start Proccess App =========="

# Function to check if a service is up
check_service() {
    local url=$1
    local name=$2
    echo "Checking $name service..."
    until curl -s $url | grep '"status":"UP"' > /dev/null; do
        sleep 1
    done
    echo "$name service is up."
}

# Java Version
echo "Verifying Java Version..."
java -version
if [ $? -ne 0 ]; then
    echo "Java is not installed or not found in PATH."
    exit 1
fi
sleep 2

# Start the discovery service in the background
echo "Starting discovery service..."
java -jar discovery/target/discovery.jar &
DISCOVERY_PID=$!

# Wait for the discovery service to be up
check_service "http://localhost:8081/actuator/health" "Discovery"
if [ $? -ne 0 ]; then
    echo "Failed to start Discovery service."
    exit 1
fi

# Start the gateway service in the background
echo "Starting gateway service..."
java -jar gateway/target/gateway.jar &
GATEWAY_PID=$!

# Wait for the gateway service to be up
check_service "http://localhost:8080/gateway/actuator/health" "Gateway"
if [ $? -ne 0 ]; then
    echo "Failed to start Gateway service."
    exit 1
fi

# Start the auth service in the background
echo "Starting auth service..."
java -jar auth/target/auth.jar &
AUTH_PID=$!

# Wait for the auth service to be up
check_service "http://localhost:8083/actuator/health" "Auth"
if [ $? -ne 0 ]; then
    echo "Failed to start Auth service."
    exit 1
fi

# Start the back-end service in the background
echo "Starting back-end service..."
java -jar back-end/target/back-end.jar &
BACKEND_PID=$!

# Wait for the back-end service to be up
check_service "http://localhost:8082/actuator/health" "Back-end"
if [ $? -ne 0 ]; then
    echo "Failed to start Back-end service."
    exit 1
fi

echo "All services started successfully."

# Optionally wait for all background processes to complete (if needed)
wait $DISCOVERY_PID
wait $GATEWAY_PID
wait $AUTH_PID
wait $BACKEND_PID
