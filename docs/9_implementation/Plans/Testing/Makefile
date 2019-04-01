PDFLATEX=pdflatex
OPEN=open

all:
	@echo "Pass through 'pdf|clean' to use"

pdf:
	$(PDFLATEX) main
	$(OPEN) main.pdf

clean:
	$(RM) -f *.log *.aux *.toc *.bbl *.lot *.lof *.bcf *.blg *.ilg *.nlo *.nls *.run.xml

cleanall:
	$(RM) -f main.pdf
	$(RM) -f *.log *.aux *.toc *.bbl *.lot *.lof *.bcf *.blg *.ilg *.nlo *.nls *.run.xml
