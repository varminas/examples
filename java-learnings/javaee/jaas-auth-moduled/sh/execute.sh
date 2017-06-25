#!/bin/bash

java -cp target/acn-module-1.0-SNAPSHOT.jar:target/login-module-1.0-SNAPSHOT.jar -Djava.security.auth.login.config==sample_jaas.config sample.SampleAcn
