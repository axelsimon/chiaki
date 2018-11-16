
cmake_minimum_required(VERSION 3.2)

project(chiaki)

option(CHIAKI_ENABLE_TESTS "Enable tests for Chiaki" ON)

add_subdirectory(lib)
add_subdirectory(gui)

if(CHIAKI_ENABLE_TESTS)
	enable_testing()
	add_subdirectory(test)
endif()