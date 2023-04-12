/*
 * AdministratorDashboardRepository.java
 *
 * Copyright (C) 2012-2023 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.lecturer.dashboard;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.datatypes.Nature;
import acme.framework.components.accounts.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Lecturer;

@Repository
public interface LecturerDashboardRepository extends AbstractRepository {

	@Query("select count(distinct l) from Lecture l inner join LectureCourse lc on l = lc.lecture inner join Course c on lc.course = c where c.lecturer = :lecturer and l.lectureType = :nature")
	Optional<Integer> totalLecturesByType(Lecturer lecturer, Nature nature);

	@Query("select avg(l.estimatedLearningTime) from Lecture l inner join LectureCourse lc on l = lc.lecture inner join Course c on lc.course = c where c.lecturer = :lecturer")
	Optional<Double> averageLearningTimeOfLectures(Lecturer lecturer);

	@Query("select stddev(l.estimatedLearningTime) from Lecture l inner join LectureCourse lc on l = lc.lecture inner join Course c on lc.course = c where c.lecturer = :lecturer")
	Optional<Double> deviationLearningTimeOfLectures(Lecturer lecturer);

	@Query("select min(l.estimatedLearningTime) from Lecture l inner join LectureCourse lc on l = lc.lecture inner join Course c on lc.course = c where c.lecturer = :lecturer")
	Optional<Double> minimumLearningTimeOfLectures(Lecturer lecturer);

	@Query("select max(l.estimatedLearningTime) from Lecture l inner join LectureCourse lc on l = lc.lecture inner join Course c on lc.course = c where c.lecturer = :lecturer")
	Optional<Double> maximumLearningTimeOfLectures(Lecturer lecturer);

	@Query("select sum(l.estimatedLearningTime) from Course c join LectureCourse lc on c = lc.course join Lecture l on lc.lecture = l where c.lecturer = :lecturer group by c")
	Collection<Double> findManyEstimatedLearningTimeByCourse(Lecturer lecturer);

	@Query("select l from Lecturer l where l.userAccount.id = :id")
	Lecturer findOneLecturerByUserAccountId(int id);

	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount findOneUserAccountById(int id);
}