package VTNgoc;
/**
 *
 * @author Vu Tuan Ngoc
 * 
 * để bọc tham số truyền vào push cùng với hàm
 */
 public class FunctionWrapper<T> {
    private final Function<T> function;
    private final T parameter;

    public FunctionWrapper(Function<T> function, T parameter) {
        this.function = function;
        this.parameter = parameter;
    }

    public void executeFunction() {
        function.execute(parameter);
    }
    }