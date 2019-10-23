#include <stdio.h>
#include "cal.h"

JNIEXPORT jint JNICALL Java_cal_add(JNIEnv *env,jobject object,jint n1,jint n2)
{
	return n1+n2;
}

JNIEXPORT jint JNICALL Java_cal_sub(JNIEnv *env,jobject object,jint n1,jint n2)
{
	return n1-n2;
}
