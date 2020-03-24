#!/bin/bash
echo "Service Solver"
export JAVA_OPTS="-Xmx10G"
#java -jar ./build/libs/solver_nn-1.0.1.jar -Xmx10G --server.port=5550 --spring.profiles.active=prod
./gradlew bootRun --args='--server.port=5550 --spring.profiles.active=prod'
exit 0
