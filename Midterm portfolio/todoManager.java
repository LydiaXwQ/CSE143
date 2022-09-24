
// todoManager models a todo list which can allows user to add, delete 
// their todo tasks. When a user finishes his/her task, he can mark it, and 
// tasks which is finished will stay at the bottom, the unfinished tasks
// will always appear at the very top of the list. User can also use it to remind
// important tasks at the time they given.

public interface todoManager {
    //return the whole todo list 
    public List<String> list;
    
    //add a task to the list
    public void add(String task);

    //delete the task from the list
    public void delete(String task);

    //add a check mark after the given task
    public void done(String task);

    //accept the content needs to be remind, return back the content
    //at a given time.
    public String alarm(String content, String time);

    //prioritize tasks that are not done to be shown at the top
    public void prioritize(); 
}
