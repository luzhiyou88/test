package com.education.classroom.core.modules.spadmin.lesson.entity;

import java.util.List;

import com.education.classroom.core.persistence.DataEntity;

public class SpLessonForm extends DataEntity<SpLessonForm>{

	private static final long serialVersionUID = 3122388154400772282L;
	private List<SpLesson> lesson;

	public List<SpLesson> getLesson() {
		return lesson;
	}

	public void setLesson(List<SpLesson> lesson) {
		this.lesson = lesson;
	}
	
	
}
