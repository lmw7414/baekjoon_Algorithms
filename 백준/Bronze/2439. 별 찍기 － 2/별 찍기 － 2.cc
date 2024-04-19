#include <stdio.h>

int main()
{
	int num;
	scanf("%d", &num);
	for (int i = 1; i <= num; i++)
	{
		for(int k=num-i; k>0; k--)
			printf(" ");
		for (int j = 1; j <= i; j++)
		{
			
			printf("*");
		}
		
		puts("");
	}
}