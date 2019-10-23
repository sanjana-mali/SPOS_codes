#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(void)
{
	char *ps[]={"ps",NULL};
	char *ls[]={"ls",NULL};
	char *join[]={"join","file1","file2",NULL};
	pid_t pid1,pid2;
	int ch=0;
	do
	{
		printf("\n1.Join \n2.PS \n3.Fork \n4.Wait \n5.Exec \n6.Exit\nEnter your choice:");
		scanf("%d",&ch);
		switch(ch)
		{
		case 1:
			execvp(join[0],join);
			break;

		case 2:
			execvp(ps[0],ps);
			break;

		case 3:
			printf("\nParent PID=%d",getpid());
			pid1=fork();
			if(pid1==0)
			{
				printf("\nChild PID=%d",getpid());
				sleep(2);
				printf("\nExiting Child!");
				exit(1);
			}
			printf("\nPID at the end:%d",getpid());
			break;

		case 4:
			pid2=fork();
			int a=0,b=0;
			if(pid2==0)
			{
				printf("\nChild PID=%d",getpid());
				a++;
				b++;
				printf("\nIn child process=\na=%d\nb=%d",a,b);
				exit(1);
			}
			if(pid2>0)
			{
				printf("\nParent PID=%d",getpid());
				a=10;
				b=10;
				printf("\nIn Parent process=\na=%d\nb=%d",a,b);
				printf("\nPID of dead child=%d",wait(NULL));
			}
			break;

		case 5:
			execvp(ls[0],ls);
			break;
		}
	}while(ch!=6);

	return EXIT_SUCCESS;
}

