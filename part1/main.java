#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#include <math.h>

void clrscr ();
int menu ();
void InsertEmployee ();
void GetAllEmployee ();
void GetEmployeeById ();
void DeleteEmployeeById ();

struct Employee_Data {
  int id;
  char name[125];
  float salary;
  struct Employee_Data *Next;
  struct Employee_Data *Prev;
}

struct Employee_Data *First;
struct Employee_Data *Last;

int main ()
{
  setlocale (LC_ALL, "Portuguese");
  int menuSelected;
  clrscr ();
  printf ("LISTA DUPLAMENTE ENCADEADA ");

  do
    {
      menuSelected = menu ();
      switch (menuSelected)	{
	case 0:
	  break;
	case 1:
	  clrscr ();
	  InsertEmployee ();
	  break;
	case 2:
	  clrscr ();
	  DeleteEmployeeById ();
	  break;
	case 3:
	  clrscr ();
	  GetEmployeeById ();
	  break;
	case 4:
	  clrscr ();
	  GetAllEmployee ();
	  break;
	default:
	  clrscr ();
	  printf ("----- OPCAO INVALIDA -----\n\n");
	  printf ("----- TENTE NOVAMENTE!!! -----\n\n");
	  break;
	}
    }
  while (menuSelected != 0);
  return 0;

}

void
clrscr ()
{
  system ("cls||clear");
}

int
menu ()
{
  int menuSelected = 0;

  printf ("\n\n ------ MENU DE OPCOES ------ \n");

  printf ("1 - Incluçao \n");
  printf ("2 - Remoção \n");
  printf ("3 - Busca \n");
  printf ("4 - Listagem \n");
  printf ("0 - SAIR !!! \n");

  printf ("\n\n Escolha a opção desejada:  ");
  scanf ("%d", &menuSelected);

  return menuSelected;
}


void InsertEmployee (int newId, char newName, float newSalary) {

  struct Employee_Data *aux;

  aux = (struct Employee_Data *) malloc (sizeof (struct Employee_Data));

  aux->id = newId;
  aux->name = newName;
  aux->salary = newSalary;

  Last->Next = aux;
  aux->Prev = Last;

  Last = Last->Next;
  aux->Next = NULL;
}

void GetAllEmployee ()
{

  struct Employee_Data *aux;

  aux = First->Next;

  printf ("\n\n -- LISTAGEM DE FUNCIONARIOS --");

  while (aux !== NULL)
    {
      printf ("\n\n ---------------------");
      printf ("\n\n Matricula: %d", aux->id);
      printf ("\n Nome: %s", aux->name);
      printf ("\n Salario: %f", aux->salary);
      printf ("\n\n---------------------");

      aux = aux->Next;
    }
}

void GetEmployeeById (int selectedId)
{

  struct Employee_Data *aux;

  int flag = 0;

  aux = First->Next;

  printf ("\n\n -- PESQUISA DE FUNCIONARIOS --");

  while (aux !== NULL)
    {

      if (aux->id == selectedId)
	{
	  printf ("\n\n Encontramos esse resultado para a pesquisa: ");
	  printf ("\n\n ---------------------");
	  printf ("\n\n Matricula: %d", aux->id);
	  printf ("\n Nome: %s", aux->name);
	  printf ("\n Salario: %f", aux->salary);
	  printf ("\n\n---------------------");

	  flag = 1;
	}
      else
	aux = aux->Next;
    }
  if (flag == 0)
    printf ("\n\n NC#o encontramos nenhum funcionario com o id: %d ",
	    selectedId);
}


void DeleteEmployeeById (int selectedId)
{
  struct Employee_Data *aux;
  int flag = 0;

  aux = First->Next;

  while (aux != NULL)
    {
      if (aux->id == selectedId)
	{

	  printf ("\n\n O funcionario, %s, foi REMOVIDO! \n", aux->name);

	  aux->Prev->Next = aux->Next;
	  aux->Next->Prev = aux->Prev;

	  aux = NULL;
	  flag = 1;

	}
      else
	{
	  aux = aux->Prox;

	}
    }

  free (aux);
  if (flag == 0)
    {
      printf ("Funcionario %d nao existe!\n", selectedId);
    }
}
