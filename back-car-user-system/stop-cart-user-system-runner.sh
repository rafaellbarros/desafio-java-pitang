#!/bin/bash

echo "========== Stop Proccess App =========="

# Array of ports to kill
ports=(8080 8081 8082 8083)

# Function to kill process running on a given port
kill_process_on_port() {
    local port=$1
    local pid

    # Get the process ID (PID) running on the port
    pid=$(lsof -t -i:$port)

    # If there's a process running on the port, kill it
    if [ -n "$pid" ]; then
        echo "Killing process on port $port (PID: $pid)"
        kill -9 $pid
    else
        echo "No process found running on port $port"
    fi
}

# Loop through the array of ports and kill processes
for port in "${ports[@]}"; do
    kill_process_on_port $port
done
