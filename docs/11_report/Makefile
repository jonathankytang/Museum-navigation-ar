PDFLATEX=pdflatex
BIBER=biber
OPEN=open
MAKEINDEX=makeindex
TEXCOUNT=texcount

all:
	@echo "Pass through 'pdf|count|clean|cleanall' to use"

pdf:
	$(PDFLATEX) main
	$(BIBER) main
	$(MAKEINDEX) main.nlo -s nomencl.ist -o main.nls
	$(PDFLATEX) main
	$(PDFLATEX) main
	$(OPEN) main.pdf

count:
	$(TEXCOUNT) -total chapters/*.tex

clean:
	$(RM) -f *.log *.aux *.toc *.bbl *.lot *.lof *.bcf *.blg *.ilg *.nlo *.nls *.run.xml *.out

cleanall:
	$(RM) -f main.pdf
	$(RM) -f *.log *.aux *.toc *.bbl *.lot *.lof *.bcf *.blg *.ilg *.nlo *.nls *.run.xml *.out
