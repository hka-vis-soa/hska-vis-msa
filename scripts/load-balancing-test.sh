#!/bin/bash
#
# Script: load-balancing-test.sh
# Description: This script performs multiple curl requests to a specified URL and saves the request URL
#              and the response header attribute "Pod-Identifier" into a CSV file.
#              The script runs in parallel, with each script instance identified by a unique script ID.
#
# Usage: ./load-balancing-test.sh <script_id> <num_requests>
#   - script_id: The identifier number for the script.
#   - num_requests: The number of requests to be made.
#
# Example: ./load-balancing-test.sh 1 100
#   - This will execute the script with script ID 1 and make 100 requests.
#

# Check if the CSV file exists, create it if necessary
if [[ ! -f requests.csv ]]; then
    echo "Script ID,URL,Pod-Identifier" > requests.csv
fi

# Function to perform a single request and save the result to CSV
perform_request() {
    local script_id=$1
    local url="http://localhost:8080/categories/$((1 + RANDOM % 5))"
    local response=$(curl -s -I $url)
    local pod_identifier=$(echo "$response" | awk -F': ' '/Pod-Identifier/ { print $2 }')

    echo "$script_id,$url,$pod_identifier" >> requests.csv
}

# Script ID (passed as first parameter)
script_id=$1

# Number of requests to be made (passed as second parameter)
num_requests=$2

# Loop to make the requests in parallel
for ((i=1; i<=num_requests; i++)); do
    perform_request $script_id &
	printf "Requests completed: %d/%d\r" $i $num_requests
done

# Wait for all background processes to finish
wait

echo "All requests completed!"
