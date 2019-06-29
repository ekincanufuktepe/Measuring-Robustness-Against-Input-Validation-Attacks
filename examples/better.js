/**
 * This code works for valid input, and will throw an exception for invalid input.
 * Now we know an error occurred and can do something about it.
 * 
 * Ref: http://catchvar.com/defensive-and-fail-fast-programming-the-right-way.html
 */

// better
public List<String> getFriendNames(User user) throws InvalidFriendListException {
    if (user != null) {
        List<User> users = user.getFriends();
        List<String> names = new ArrayList<String>();

        if (users != null) {
            for (User user : users) {
                String name = user.getName();
                names.add(name == null ? "" : name);
            } 
        } else {
            throw new InvalidFriendListException();
        }

    } else {
        throw new InvalidFriendListException();
    }

    return names;
}