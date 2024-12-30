package be.springboot.pp.dsalgo.stacks;

import java.util.Stack;

public class S002_lc_0071 {

    public static String simplifyPath(String path) {
        // Split the path into components based on '/'
        String[] components = path.split("/");
        Stack<String> stack = new Stack<>();

        // Process each component
        for (String component : components) {
            if (component.equals(".") || component.isEmpty()) {
                // Ignore '.' and empty components
                continue;
            } else if (component.equals("..")) {
                // Go up a directory if possible
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                // Push valid directory names
                stack.push(component);
            }
        }

        // Build the canonical path
        StringBuilder result = new StringBuilder();
        for (String dir : stack) {
            result.append("/").append(dir);
        }

        // Return root '/' if the stack is empty
        return result.length() > 0 ? result.toString() : "/";
    }

    public static void main(String[] args) {
        // Example test cases
        System.out.println(simplifyPath("/home/"));                   // Output: "/home"
        System.out.println(simplifyPath("/home//foo/"));              // Output: "/home/foo"
        System.out.println(simplifyPath("/home/user/Documents/../Pictures")); // Output: "/home/user/Pictures"
        System.out.println(simplifyPath("/../"));                     // Output: "/"
        System.out.println(simplifyPath("/.../a/../b/c/../d/./"));    // Output: "/.../b/d"
    }
}
