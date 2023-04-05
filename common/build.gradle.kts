//import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
//	java
	id("java-library")
//	id("org.springframework.boot") version "3.0.5"
//	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

//configurations {
//	compileOnly {
//		extendsFrom(configurations.annotationProcessor.get())
//
//	}
//}

repositories {
	mavenCentral()
}

dependencies {
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	implementation("com.fasterxml.jackson.core:jackson-databind:2.0.1")
//	testImplementation("org.springframework.boot:spring-boot-starter-test")
//		implementation("org.springframework.boot:spring-boot-starter")

}

tasks.withType<Test> {
	useJUnitPlatform()
}

//val bootJar: BootJar by tasks
//bootJar.enabled = false
//val jar: Jar by tasks
//jar.enabled = true