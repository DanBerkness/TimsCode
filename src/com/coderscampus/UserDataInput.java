package com.coderscampus;

public interface UserDataInput {
	String promptUsernameToUpdate();

	void promptUpdateName(User currentUser);

	void promptUpdatePassword(User currentUser);

	void promptUpdateUsername(User currentUser);

	int promptOptions(User currentUser);

}
