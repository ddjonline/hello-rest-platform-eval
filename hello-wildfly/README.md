# Wildfly
Install Wildfly and set the `WILDFLY_HOME` environment variable

# start Wildfly `$WILDFLY_HOME/bin/standalone.sh`

# shutdown Wildfly (after Ctl+C) `$WILDFLY_HOME/bin/jboss-cli.sh --connect command=:shutdown`

# see endpoing `http://localhost:8080/hello`
# see long-running computation endpoint `http://localhost:8080/naptime`