import lockout.FailureEventListener
import lockout.SuccessEventListener

beans = {

	failureEventListener(FailureEventListener) {
		userService = ref('userService')
	}

	successEventListener(SuccessEventListener) {
		userService = ref('userService')
	}
}
