# HabSat backend

## Setup

First you need to initialize git submodules. To do so, run:

```bash
git submodule init
git submodule update
```

## Build

To build war artifact, run

```bash
mvn clean package
```

Artifact will be placed in target directory. War contains and
serves [frontend](https://github.com/grupacosmo/habsat-frontend) files.