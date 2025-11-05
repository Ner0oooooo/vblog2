#!/bin/bash
java --add-opens java.base/java.lang=ALL-UNNAMED \
     --add-opens java.base/java.lang.reflect=ALL-UNNAMED \
     --add-opens java.base/java.io=ALL-UNNAMED \
     --add-opens java.base/java.util=ALL-UNNAMED \
     --add-opens java.base/java.text=ALL-UNNAMED \
     --add-opens java.base/java.math=ALL-UNNAMED \
     --add-opens java.base/java.net=ALL-UNNAMED \
     --add-opens java.base/java.time=ALL-UNNAMED \
     --add-opens java.base/java.security=ALL-UNNAMED \
     --add-opens java.base/java.util.concurrent=ALL-UNNAMED \
     --add-opens java.base/sun.nio.ch=ALL-UNNAMED \
     --add-opens java.base/sun.security.util=ALL-UNNAMED \
     --add-opens java.xml/com.sun.org.apache.xerces.internal.jaxp=ALL-UNNAMED \
     -jar target/vblog.jar