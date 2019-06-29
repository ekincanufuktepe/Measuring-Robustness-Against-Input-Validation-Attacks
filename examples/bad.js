/**
 * I do not consider this defensive programming. 
 * This is sweep under the rug programming. 
 * If an error occurs you won’t know.
 * 
 * Ref: http://catchvar.com/defensive-and-fail-fast-programming-the-right-way.html
 */

// bad
public List<String> getFriendNames(User user) {
    List<String> names = new ArrayList<String>();

    if (user != null) {
        List<User> users = user.getFriends();

        if (users != null) {
            for (User user : users) {
                String name = user.getName();
                names.add(name);
            } 
        }
    }

    return names;
}