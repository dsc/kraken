#! /bin/bash

# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

export OOZIE_CONFIG=/etc/oozie/conf
export OOZIE_DATA=/var/lib/oozie
export OOZIE_LOG=/var/log/oozie
export OOZIE_CATALINA_HOME=/usr/lib/bigtop-tomcat
export CATALINA_TMPDIR=/var/lib/oozie
export CATALINA_PID=/var/run/oozie/oozie.pid
export CATALINA_BASE=/usr/lib/oozie/oozie-server
export CATALINA_OPTS=-Xmx1024m
export OOZIE_HTTPS_PORT=11443
export OOZIE_HTTPS_KEYSTORE_PASS=password
export CATALINA_OPTS="$CATALINA_OPTS -Doozie.https.port=${OOZIE_HTTPS_PORT}"
export CATALINA_OPTS="$CATALINA_OPTS -Doozie.https.keystore.pass=${OOZIE_HTTPS_KEYSTORE_PASS}"
