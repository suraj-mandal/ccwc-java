# CCWC (Custom Word Checker) Tool

The custom word checker tool has been created as a coding challenge
from [codingchallenges.fyi](https://codingchallenges.fyi/challenges/challenge-wc).
Visit the website to gather more insights on how does the tool work.

## Architecture Diagram

Provided below is a concise architecture diagram of how the application behaves.

![architecture diagram](./assets/application_design.png)

## Requirements

1. The given executable was built using **GraalVM-21.0.5** on **Ubuntu 24.04**.

## Steps to build the executable

1. Run the following command.

```bash
  make
```

## Usages

Right now the following _flags_ are supported by the **ccwc** tool

| Flag | Description                                    |
|------|------------------------------------------------|
| -w   | Return the number of words in the input stream |
| -l   | Return the number of lines in the input stream |
| -m   | Return the number of chars in the input stream |
| -c   | Return the number of bytes in the input stream | 

#### In the absence of any flag, the **ccwc
** tool will display the number of words, lines and bytes from the input stream.

### The tool can be used in the following ways

```bash
  ccwc -w test.txt
```

```bash
  cat test.txt | ccwc -l
```