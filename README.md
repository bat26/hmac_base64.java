HMAC_BASE64
=========


##About

hmac_base64 was created for Solaris and Windows servers that were missing utilities to create Hash-based Message Authentication codes that could then be encoded in Base64/32 format. 
Nowadays in modern Unix systems with the latest packages of OpenSSL and Base64 you can easily pipe the output from one process to another.
However if you are stuck due to server environment limitations, then this tool can help you out.


## Shell Arguments

There are two main operations in HMAC Base:

**`hmac_base`** generates a HMAC and then proceeds to encode it using a Base method before outputting.

**`base`** allows the encoding or decoding of inputs which can then be outputted (this is useful for secret keys that have been encoded in base32/64).

*Note* - all arguments use POSIX notation using a single `-` as a delimiter.

###hmac_base
Requires the following arguments:

 - `-k [key]` secretkey; a value that is used to generate the hash.
 - `-h [hmacmethod]` hashmethod; the specific HMAC method to generate the hash. The following HMAC methods are accepted:
	 - HmacMD5
	 - HmacSHA1
	 - HmacSHA256
 - `-i [input]` input; the source of input. The program will check if a filepath or literal string has been given here.
 - `-encode [encodemethod]` specifies the base encode method. The available Base methods are:
	 - Base32
	 - Base64

###base
 - `-i [input]` input; the source of input. The program will check if a filepath or literal string has been given here.
 - `-encode [encodemethod]` specifies the base encode method. The available Base methods are:
	 - Base32
	 - Base64

###optional arguments

 - `-d` debug; instructs the program to output all steps to a log file called hmac_base.log.
 - `-f [filename]` output file; final output will be written to. Not fully tested with filepaths as a value, if it does not work then simply output to the current working directory and move the file using a shell/batch script.
 - `-c [regex pattern]` cleanse input. The input will be cleansed using a regex pattern and any characters matching the criteria will be erased.
 - `-l` cleanse new lines. The input will have any new lines removed
 - `-w` cleanse whitespace. The input will have all whitespace characters removed.

## Code Examples

`-hmac_base -k secretsquirrel -h HmacSHA1 -i "input.txt" -encode base32`

This will perform a full HMAC generation and subsequent Base encode in Base32. The secret key is "secretsquirrel" which is being used by HMAC method "HmacSHA1" to hash a value taken from the input.txt.
Since there was no -f argument the value will be returned via standard output.
 
`-base -i Firefly -encode base64 -f Serenity.txt -debug`

This will perform a base64 encoding of the string "Firefly". The encoded value will be outputted to a file called "Serenity.txt".


`-base -i Arrakis.txt -encode base32 -f Dune.txt -w -l`

This will perform a base32 encoding of the contents of "Arrakis.txt". Before the encoding is performed all whitespace and newline characters will be removed. The resulting output will be written to the file "Dune.txt".

## Installation

Download the source files to your machine or IDE.
Compile the Java code into a runnable JAR file.
If you are using FTP to deploy, then make sure you use a binary transfer mode to prevent corruption of the JAR file.
Ensure the directory that you are executing HMAC Base is writable (CHMOD 755).

## Requirements

Minimum of Java version 1.6.0.29

If in doubt of JRE on the system, type ` java -version`

## License

MIT License

## Contact

loosebruce.co.uk



