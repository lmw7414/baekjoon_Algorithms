#include <stdio.h>

int main()
{
	int num;
	scanf("%d", &num);
	for (int i = 0; i < num; i++)
	{
		for (int k = num; k > num - i; k--)
			printf(" ");
		for (int j = 0; j < num - i; j++)
			printf("*");
		puts("");
	}
}