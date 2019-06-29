/**
 * There are numerous opportunities for null pointer exceptions. 
 * In addition, we may get nulls in our return list. 
 * 
 * Ref: http://catchvar.com/defensive-and-fail-fast-programming-the-right-way.html
 */

function getNames

// no defensive programming
public List<String> getFriendNames(User user) {
    List<User> users = user.getFriends();
    List<String> names = new ArrayList<String>();

    for (User user : users) {
        names.add(user.getName());
    }

    return names;
}