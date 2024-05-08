package TodoList;

import java.util.Locale;
import java.util.Scanner;

public class AppFormatado {

    // todo: 1. Adicionar método editar o nome da tarefa, descrição e às etapas;
    // todo: 2. Implementar os estados às tarefas;
    // todo: 3. Criar funções estáticas para adicionar qualidade ao código;
    // todo: 4. Verificar e implementar fluxo do Software, impedir a quebra ao errar

    public static void presents() {
        /* Apresentação */
        System.out.println("#################### Bem vindo ao App ToDo List ####################");
        System.out.println("Leia a documentação do App.");
        System.out.println("1. Crie uma nova tarefa.");
        System.out.println("2. Adicione uma descrição (opcional), você pode citar elementos da tarefa.\n" +
                "Caso deseje deixar sem descrição, por favor pressione apenas Enter.");
        System.out.println(
                "3. Adicione etapas ou passos (opcional) a seguir para organizar a execução de sua tarefa da melhor forma.");
        System.out.println("Obs: você pode excluir ou editar passos e tarefas.");
        System.out.println(
                "Obs: você pode adicionar um estado à passos e tarefas, com as opções: \n     1. Para iniciar.\n" +
                        "     2. Em desenvolvimento.\n     3. Tarefa finalizada.\n");
    }

    public static void mainMenu() {
        /* Menu principal */
        System.out.println("_____Menu_____");
        System.out.println("0. Fechar programa.\n" +
                "1. Adicionar tarefa.\n" +
                "2. Adicionar planejamento por etapa.\n" +
                "3. Remover etapa ou tarefa.\n" +
                "4. Editar etapa ou tarefa.\n" +
                "5. Mostrar tarefas salvas.");
        System.out.print("Por favor, entre com um número correspondente: ");
    }

    /* Método estático de Adicionar Nova Tarefa */
    public static Task addNewTask(Task task1, Scanner scan) {
        System.out.print("Nome da tarefa: ");
        String taskName = scan.nextLine();
        task1.setNameTask(taskName);
        System.out.print("Adicionar descrição: ");
        String taskDescription = scan.nextLine();
        if (taskDescription.length() > 1) {
            task1.setDescription(taskDescription);
        } else {
            task1.setDescription("n/d");
        }

        return task1;
    }

    /* Método estático de Adicionar Planejamento por Etapas */
    public static Steps addStepsToNewTask(Scanner scan, Task task) {
        Steps st = new Steps();
        System.out.println("Digite a descrição da etapa: ");
        System.out.print("-> ");
        String newStep = scan.nextLine();
        st.setStep(newStep);
        return st;
    }

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        scan.useLocale(Locale.ENGLISH);

        boolean condition = true;
        presents();
        TodoList taskList = new TodoList();
        while (condition) {
            mainMenu();
            int mainMenuOpt = scan.nextInt();
            scan.nextLine();

            if (mainMenuOpt > 0 && mainMenuOpt < 6) {
                switch (mainMenuOpt) {
                    case 1:
                        /* Adicionar tarefa. */
                        System.out.println("__________ Menu de Nova Tarefa __________");
                        Task task = new Task();
                        addNewTask(task, scan);
                        taskList.addTask(task);
                        /* Adicionar etapas à tarefa atual. */
                        boolean currentNewSteps = true;
                        while (currentNewSteps) {
                            System.out.println("Adicionar nova etapa? \n1. Sim.\n2. Não.");
                            System.out.print("Por favor, digite a opção correspondente: ");
                            int response = scan.nextInt();
                            scan.nextLine();
                            if (response == 1) {
                                Steps st = addStepsToNewTask(scan, task);
                                task.addStep(st);
                            } else {
                                System.out.println("Ok, você pode adicionar etapas depois.");
                                currentNewSteps = !currentNewSteps;
                            }
                        }
                        break;
                    case 2:
                        /* Adicionar Planejamento por etapas à tarefas existentes. */
                        boolean conditionCreateNewSteps = true;
                        int createNewStepOption = 3;
                        while (conditionCreateNewSteps) {
                            System.out.println("__________ Menu de Planejamento Por Etapas. __________");
                            if (taskList.getTasks().size() == 0) {
                                System.out.println(
                                        "Você não possui nenhuma tarfea salva, por favor crie uma nova tarefa.");
                                conditionCreateNewSteps = !conditionCreateNewSteps;
                            } else {
                                System.out.print(
                                        "1. Exibir lista de tarefas.\n2. Retornar ao menu principal.\nPor favor digite a opção correspondente: ");
                                int continueOption = scan.nextInt();
                                if (continueOption == 1) {
                                    System.out.println(taskList);
                                    System.out.print("Por favor, digite o índice da terefa correspondente: ");
                                    int taskIdToSearch = scan.nextInt() - 1;
                                    scan.nextLine();
                                    Task thisTaskToNewStep = taskList.searchTask(taskIdToSearch);
                                    if (thisTaskToNewStep != null) {
                                        System.out.println("Tarefa encontrada: \n" + thisTaskToNewStep + "\n");
                                        System.out.print(
                                                "1. Adicionar etapa à tarefa.\n2. Voltar ao menu anterior.\n0. Retornar ao menu principal.\nPor favor, digite a opção correspondente: ");
                                        createNewStepOption = scan.nextInt();
                                        scan.nextLine();
                                        if (createNewStepOption == 1) {
                                            Steps st = addStepsToNewTask(scan, thisTaskToNewStep);
                                            thisTaskToNewStep.addStep(st);
                                        } else if (createNewStepOption != 2) {
                                            conditionCreateNewSteps = !conditionCreateNewSteps;
                                        }
                                    } else {
                                        System.out.println("Não foi possível encontrar uma tarefa correspondente.");
                                    }
                                } else {
                                    conditionCreateNewSteps = !conditionCreateNewSteps;
                                }
                            }
                        }
                        break;
                    case 3:
                        /* Remoção de etapas de tarefas ou tarefas */
                        System.out.println("__________ Menu De Remoção __________");
                        boolean conditionToRemove = true;
                        while (conditionToRemove) {
                            System.err.print(
                                    "1. Remover tarefa.\n2. Remover etapa de uma tarefa.\n0. Retornar ao menu principal.\n"
                                            +
                                            "Por favor, informe a opção correspondente: ");
                            int removeTaskOrStep = scan.nextInt();
                            if (removeTaskOrStep == 1) {
                                System.out.println("__________ Remoção de Tarefas __________");
                                System.out.println(taskList);
                                System.out.print("Por favor, digite o índice da terefa correspondente: ");
                                int removeTaskOption = scan.nextInt() - 1;
                                Task thisTaskToRemove = taskList.searchTask(removeTaskOption);
                                if (thisTaskToRemove != null) {
                                    System.out.println("Excluir tarefa?\n1. Sim.\n2. Não.");
                                    System.out.print("Por favor, digite a opção correspondente: ");
                                    int agree = scan.nextInt();
                                    if (agree == 1) {
                                        taskList.removeTask(thisTaskToRemove);
                                    } else if (agree != 1) {
                                        System.out.println("A tarefa não foi excluída.");
                                        break;
                                    }
                                } else {
                                    System.out.println("Não foi possível encontrar uma tarefa correspondente.");
                                }
                            } else if (removeTaskOrStep == 2) {
                                System.out.println("__________ Remoção de Etapas De Tarefas __________");
                                System.out.println(taskList);
                                System.out.print("Por favor, digite o índice da terefa correspondente: ");
                                int removeStepTaskNameOption = scan.nextInt() - 1;
                                Task thisTaskToRemove = taskList.searchTask(removeStepTaskNameOption);
                                if (thisTaskToRemove != null) {
                                    System.out.println(thisTaskToRemove);
                                    System.out.print("Por favor, digite o índice da etapa correspondente: ");
                                    int removeStepOption = scan.nextInt() - 1;
                                    Steps thisStepToRemove = thisTaskToRemove.searchStep(removeStepOption);
                                    if (thisStepToRemove != null) {
                                        System.out.println("Excluir etapa da sua tarefa?\n1. Sim.\n2. Não.");
                                        System.out.print("Por favor, digite a opção correspondente: ");
                                        int agree = scan.nextInt();
                                        if (agree == 1) {
                                            thisTaskToRemove.removeStep(thisStepToRemove);
                                            System.out.println("Etapa excluída com sucesso!");
                                            break;
                                        } else if (agree != 1) {
                                            System.out.println("A etapa não foi excluída.");
                                            break;
                                        }
                                    } else {
                                        System.out.println(
                                                "Não foi possível encontrar uma etapa correspondente nesta tarefa.");
                                    }
                                } else {
                                    System.out.println("Não foi possível encontrar uma tarefa correspondente.");
                                    break;
                                }
                            } else if (removeTaskOrStep != 1 || removeTaskOrStep != 2) {
                                conditionToRemove = !conditionToRemove;
                            }
                        }
                        break;
                    case 4:
                        /* Edição de etapas de tarefas e tarefas */
                        System.out.println("Desculpe, opção em desenvolvimento.");
                        break;
                    case 5:
                        /* Listagem de tarefas */
                        System.out.println(taskList);
                        break;
                }

            } else {
                condition = !condition;
            }
        }
        System.out.println("Ending program...");

        scan.close();
    }

}