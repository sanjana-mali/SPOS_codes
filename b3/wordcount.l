%{
int chars=0,lines=0,words=0,caps=0,small=0,digits=0;
%}

%%

\n {lines++;words++;}
[\t ] {words++;}
[a-z] {small++;}
[A-Z] {caps++;}
[0-9] {digits++;}
. {chars++;}

%%

int yywrap()
{
	return 1;
}

int main(int argc,char *argv[])
{
	yyin=fopen(argv[1],"r");
	yylex();
	int c=small+caps;
	printf("\nLines=%d",lines);
	printf("\nChars=%d",c);
	
	printf("\nWords=%d\nSmall=%d\nCaps=%d\nDigits=%d\n",words,small,caps,digits);
	return 0;
}
