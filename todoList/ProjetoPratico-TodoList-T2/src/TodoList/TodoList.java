package TodoList;

import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private List<Task> tasks = new ArrayList<>();
    private Task task;

    public TodoList() {
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public Task searchTask(int taskId) {
        return tasks.get(taskId);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Task getTask() {
        return task;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("____________________Lista De Tarefas____________________\n");
        int i = 1;
        for (Task task : getTasks()) {
            sb.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            sb.append("Tarefa -> " + i + "\n");
            sb.append("---tarefa---> " + task.getNameTask() + ".\n");
            sb.append("----------> Estado da tarefa: " + task.getTaskState() + ".\n");
            if (task.getDescription() != null) {
                sb.append("-----descrição-----> " + task.getDescription() + ".\n");
            }
            if (task.getStepsList().size() > 0) {
                sb.append("----------> Passos da tarefa: \n");
                int ii = 1;
                for (Steps s : task.getStepsList()) {
                    sb.append("-----Passo # " + ii + "\n-------passo-------> " + s.getStep() + ".\n");
                    ii++;
                }
            }
            i++;
            sb.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
        sb.append("________________________________________________________");

        return sb.toString();
    }
}