package TodoList;

import java.util.ArrayList;
import java.util.List;

public class Task {
    private String nameTask;
    private String description;

    private List<Steps> stepsList = new ArrayList<>();
    private Steps steps;

    public Task() {
    }

    public Task(String nametask, String description) {
        this.nameTask = nametask;
        this.description = description;
    }

    public Task(String nameTask, String description, Steps steps) {
        this.nameTask = nameTask;
        this.description = description;
        this.steps = steps;
    }

    public void addStep(Steps stepString) {
        stepsList.add(stepString);
    }

    public void removeStep(Steps stepToRemove) {
        stepsList.remove(stepToRemove);
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public Steps searchStep(int stepId) {
        return stepsList.get(stepId);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Steps getSteps() {
        return steps;
    }

    public List<Steps> getStepsList() {
        return stepsList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("----------------------------------");
        sb.append("Tarefa: " + getNameTask() + ".\n");
        sb.append("Descrição: " + getDescription() + ".\n");
        sb.append("Passos a seguir: \n");
        int i = 1;
        for (Steps s : stepsList) {
            sb.append("Passo #" + i + "\n\t" + s.getStep() + ".\n");
            i++;
        }
        sb.append("----------------------------------");

        return sb.toString();
    }
}
