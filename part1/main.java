#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <locale.h>
#include <math.h>

void clrscr ();
int menu ();

struct employee_Data {
  int id;
  char *name;
  float salary;
  struct employee_Data *next;
  struct employee_Data *prev;
};

typedef struct employee_Data Employee_Data;

Employee_Data *initialize(void)
{
    return NULL;
}

    Employee_Data *InsertEmployeeInList(Employee_Data *l, int newId, char *newName, float newSalary) {
        
        Employee_Data *newEmployee = (Employee_Data *)malloc(sizeof(Employee_Data));
        
        newEmployee->id = newId;
        newEmployee->name = newName;
        newEmployee->salary = newSalary;
        
        newEmployee->prev = NULL;
        newEmployee->next = l;
        if (l != NULL)
            l->prev = newEmployee;

        return newEmployee;
    }
    
    Employee_Data *InsertEmployee(Employee_Data *l)
    {
        struct employee_Data *aux;
        
        int lastId;
        
        for(aux=l; aux!= NULL; aux = aux->next) {
            if (aux->next == NULL){
                lastId = aux->id;
            }
        }
        
        int newId = lastId + 1;
        char newName[125];
        float newSalary;
        
        printf ("\n\n -- CADASTRO DE FUNCIONARIO -- \n\n"); 
        
        printf("\n Digite o nome do funcionário: ");
        scanf("%s", newName);
        
        printf("\n Digite o salario do funcionário: ");
        scanf("%f", &newSalary);
        
    
    return InsertEmployeeInList(l, newId, newName, newSalary);
}

void GetAllEmployee (Employee_Data *l) {

  Employee_Data *aux; 

  printf ("\n\n -- LISTAGEM DE FUNCIONARIOS --");

  for(aux=l; aux!= NULL; aux = aux->next)	
    {
      printf ("\n\n ---------------------");
      printf ("\n\n Matricula: %d", aux->id);
      printf ("\n Nome: %s", aux->name);
      printf ("\n Salario: %f", aux->salary);
      printf ("\n\n---------------------");
    }
}

void GetEmployeeById(Employee_Data *l) {
    
    Employee_Data *aux; 
    int selectedId;
    int flag = 0;

  printf ("\n\n -- PESQUISA DE FUNCIONARIOS --");
  
  printf ("\n\n Digite um id: ");
  scanf("%d", &selectedId);
  
  
   for(aux=l; aux!= NULL; aux = aux->next)	{
      if(selectedId == aux->id) {
          
       flag = 1;
       
       printf ("\n\n Encontramos esse resultado para a pesquisa: ");
       
	   printf ("\n\n ---------------------");
	   
	   printf ("\n\n Matricula: %d", aux->id);
	   printf ("\n Nome: %s", aux->name);
	   printf ("\n Salario: %f", aux->salary);
	   
	   printf ("\n\n---------------------");
      }
    }

  if (flag == 0)
    printf ("\n\n Nao encontramos nenhum funcionario com o id: %d ",
	    selectedId);
}

Employee_Data* DeleteEmployeeById (Employee_Data *l)
{
  Employee_Data *aux;
  int flag = 0;
  int selectedId;
  
  printf ("\n\n -- REMOVER FUNCIONARIO --");
  
  printf ("\n\n Digite um id: ");
  scanf("%d", &selectedId);

  for(aux=l; aux!= NULL; aux = aux->next)
    {
      if (aux->id == selectedId) {

	  printf ("\n\n O funcionario, %d, foi REMOVIDO! \n", aux->id);

	  aux->prev->next = aux->next;
	  aux->next->prev = aux->prev;

	  aux = NULL;
	  flag = 1;

	  } else {
	  l = aux->next;
	    }
    }

  free (aux);
  if (flag == 0)
    {
      printf ("Funcionario %d nao existe!\n", selectedId);
    }
    return l;
}


void clrscr () {
  system ("cls||clear");
}

int menu () {
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


int main ()
{
  setlocale (LC_ALL, "Portuguese");
  int menuSelected;
  clrscr ();
  Employee_Data *l;
  l = initialize();
  printf ("LISTA DUPLAMENTE ENCADEADA ");

  do
    {
      menuSelected = menu ();
      switch (menuSelected)	{
	case 0:
	  break;
	case 1:
	  clrscr ();
	  l = InsertEmployee(l);
	  break;
	case 2:
	  clrscr ();
	  l = DeleteEmployeeById(l);
	  break;
	case 3:
	  clrscr ();
	  GetEmployeeById(l);
	  break;
	case 4:
	  clrscr ();
	  GetAllEmployee(l);
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



