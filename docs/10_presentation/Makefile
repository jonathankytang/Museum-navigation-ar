PDFLATEX=pdflatex
BIBER=biber
OPEN=open
MAKEINDEX=makeindex
TEXCOUNT=texcount

all:
	@echo "Pass through 'pdf|clean' to use"

pdf:
	$(PDFLATEX) main
	$(PDFLATEX) main
	$(OPEN) main.pdf

cleanall:
	$(RM) -f main.pdf
	$(RM) -f main*.pdf
	$(RM) -f *.log *.aux *.toc *.bbl *.lot *.lof *.bcf *.blg *.ilg *.nlo *.nls *.run.xml *.nav *.out *.snm
	
clean:
	$(RM) -f *.log *.aux *.toc *.bbl *.lot *.lof *.bcf *.blg *.ilg *.nlo *.nls *.run.xml *.nav *.out *.snm
