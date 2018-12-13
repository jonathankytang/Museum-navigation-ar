#!/bin/bash

pdflatex main
makeindex main.nlo -s nomencl.ist -o main.nls
pdflatex main
open main.pdf
