# Base64 encoder/decoder
Shell util is written on pure java, use base 64 to encode or decode content, the input can be taken from file, console, clipboard and saved in file etc
# How to run?

`
git clone git@github.com:MaximBogun/base64jrp.git
`

`cd ./base64jrp`

`mvn package spring-boot:repackage     
`

`docker build . -t base64jrp 
`

`docker run -it -p 8080:8080 base64jrp 
`

`Input help, help enc, help dec`