document.addEventListener('DOMContentLoaded', function () {
    let emp_num_value = {
        emp_num: document.querySelector("#session_emp_num").value
    }
    $(function () {
        var request = $.ajax({
            url: "/bookingList",
            method: "get",
            contentType: "application/json; charset=utf-8",
            dataType: "JSON"
        });

        var calendarEl = document.getElementById('calendar');

        request.done(function (data) {

            var calendarEl = document.getElementById('calendar');

            var calendar = new FullCalendar.Calendar(calendarEl, {
                headerToolbar: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'listMonth,timeGridDay,timeGridWeek,dayGridMonth'
                },
                initialDate: '2022-10-12',
                navLinks: true, // can click day/week names to navigate views
                businessHours: true, // display business hours
                editable: true,
                selectable: true,
                dayMaxEventRows: true, // for all non-TimeGrid views
                themeSystem: 'bootstrap5',
                locale: `ko`,
                height: `92vh`,
                views: {
                    timeGrid: {
                        dayMaxEventRows: 4 // adjust to 6 only for timeGridWeek/timeGridDay
                    }
                },
                eventDrop: function (info) {
                    $.ajax({
                        url: "/bookingDateChange",
                        method: "POST",
                        dateType: "text",
                        data: {
                            "booking_num": info.event.id,
                            "booking_start_date": info.event.startStr.slice(0, -6),
                            "booking_end_date": info.event.endStr.slice(0, -6),
                            "emp_num": document.querySelector("#session_emp_num").value
                        },
                        success: function (success_check) {
                            if(success_check == "success"){
                                alert("?????? ????????? ?????????????????????");
                            } else if(success_check == "error"){
                                alert("????????? ????????? ????????? ???????????????.");
                                location.reload();
                            }
                        }
                    })
                },
                eventClick: function (info) {
                    booking_event_meetingroom.value = "meetingroom_" + info.event.extendedProps.meetingroom;
                    booking_event_fixtures.value = "fixtures_" + info.event.extendedProps.fixtures_num;
                    if (info.event.extendedProps.meetingroom != 0) {
                        booking_event_fixtures_box.style.height = "0px";
                        booking_event_fixtures_box.style.opacity = "0";
                        booking_event_fixtures_box.style.visibility = "hidden";
                        booking_event_meetingroom_box.style.height = "30px";
                        booking_event_meetingroom_box.style.opacity = "1";
                        booking_event_meetingroom_box.style.visibility = "visible";

                    } else if (info.event.extendedProps.fixtures_num != 0) {
                        booking_event_meetingroom_box.style.height = "0px";
                        booking_event_meetingroom_box.style.opacity = "0";
                        booking_event_meetingroom_box.style.visibility = "hidden";
                        booking_event_fixtures_box.style.height = "30px";
                        booking_event_fixtures_box.style.opacity = "1";
                        booking_event_fixtures_box.style.visibility = "visible";
                    }

                    // ?????? ??????
                    const booking_add_event = document.querySelector(".booking_add_event");
                    // ?????? ??????
                    const booking_change_event = document.querySelector(".booking_change_event");
                    // ?????? ??????
                    const booking_remove_event = document.querySelector(".booking_remove_event");
                    // ????????? ??????
                    const booking_title = document.querySelector(".booking_event_title");
                    // ????????? ?????? input
                    const booking_start_date = document.querySelector(".booking_start_date");
                    // ????????? ?????? input
                    const booking_end_date = document.querySelector(".booking_end_date");
                    // ????????? ??????
                    const booking_memo = document.querySelector(".booking_memo");

                    booking_modal.style.display = "block";
                    booking_add_event.style.display = "none";
                    booking_change_event.style.display = "block";
                    booking_remove_event.style.display = "block";

                    defualt_date(info.event.startStr.slice(0, -6), info.event.endStr.slice(0, -6));

                    let user_start_select = info.event.startStr.slice(0, -6);
                    let user_end_select = info.event.endStr.slice(0, -6);

                    booking_title.value = info.event.title;
                    booking_start_date.value = user_start_select;
                    booking_end_date.value = user_end_select;
                    booking_memo.value = info.event.extendedProps.content;

                    const user_check_box = document.querySelectorAll("input[id *= user]");
                    for (let i = 0; i < user_check_box.length; i++) {
                        user_check_box[i].checked = false;
                    }

                    // ????????? ????????? ??????
                    const attendees_all = document.querySelectorAll(".attendees");
                    for (let i = 0; i < attendees_all.length; i++) {
                        attendees_all[i].remove();
                    }

                    // ????????? ????????? ?????????
                    let attendees = info.event.extendedProps.attendees.split(",");
                    for (let i = 0; i < user_check_box.length; i++) {
                        for (let j = 0; j < attendees.length; j++) {
                            if (attendees[j] == user_check_box[i].id.slice(4)) {
                                if (user_check_box[i].checked == false) {
                                    user_check_box[i].checked = true;
                                    // =============== ?????? ????????? ===============
                                    const attendees = document.createElement("div"); // ????????? div ??????
                                    const attendees_name_box = document.createElement("div"); // ?????? div ??????
                                    const attendees_name = document.createElement("span"); // ?????? ???????????? span ??????
                                    attendees.setAttribute("class", `attendees user_${user_check_box[i].id.slice(4)}`); // ????????? div??? ????????? ??????
                                    attendees_name.innerHTML = user_check_box[i].labels[0].innerHTML; // ?????? ???????????? div??? ????????????
                                    attendees_name_box.append(attendees_name); // ??????????????? ??????
                                    attendees.append(attendees_name_box); // ??????????????? ??????
                                    booking_attendees_list_box.append(attendees); // ??????????????? ??????

                                    booking_attendees_list_box.childNodes[0].textContent = "";
                                }
                            }
                        }
                    }

                    // ???????????? ?????? ???
                    booking_change_event.addEventListener("click", function () {

                        // ????????? ???????????? ????????? ????????? ????????? ??????
                        let attendees_List = new Array;
                        for (let i = 0; i < user_check_box.length; i++) {
                            if (user_check_box[i].checked == true) {
                                attendees_List.push(user_check_box[i].id.slice(4) + ",");
                            }
                        }

                        // ????????? ???????????? ???????????? ???????????? ??????
                        let attendees_emp_code_list = "";
                        for (let i = 0; i < attendees_List.length; i++) {
                            attendees_emp_code_list += attendees_List[i];
                        }

                        let booking_event_meetingroom_code = parseInt(booking_event_meetingroom.value.slice(12));
                        let booking_event_fixtures_code = parseInt(booking_event_fixtures.value.slice(9));

                        $.ajax({
                            url: "/booking_event_change",
                            method: "POST",
                            dataType: "text",
                            data: {
                                "booking_num": info.event.id,
                                "booking_title": booking_title.value,
                                "booking_start_date": booking_start_date.value,
                                "booking_end_date": booking_end_date.value,
                                "booking_memo": booking_memo.value,
                                "meetingroom_num": booking_event_meetingroom_code,
                                "fixtures_num": booking_event_fixtures_code,
                                "booking_attendees": attendees_emp_code_list,
                                "emp_num": document.querySelector("#session_emp_num").value
                            },
                            success: function(success_check){
                                if(success_check == "success"){
                                    alert("????????? ?????????????????????.")
                                    location.reload()
                                } else if(success_check == "error"){
                                    alert("????????? ????????? ????????? ???????????????.")
                                    location.reload()
                                }
                            }
                        })
                    })

                    // ?????? ?????? ?????? ???
                    booking_remove_event.addEventListener("click", function () {

                        $.ajax({
                            url: "/booking_delete",
                            method: "POST",
                            dateType: "text",
                            data: {
                                "booking_num": info.event.id,
                                "emp_num": document.querySelector("#session_emp_num").value
                            },
                            success: function(success_check){
                                if(success_check == "success"){
                                    alert("????????? ?????????????????????.");

                                    location.reload();
                                } else if(success_check == "error"){
                                    alert("????????? ????????? ????????? ???????????????.")

                                    location.reload();
                                }
                            }
                        })

                    })

                },
                select: function (arg) {
                    // ?????? ??????
                    const booking_add_event = document.querySelector(".booking_add_event");
                    // ?????? ??????
                    const booking_change_event = document.querySelector(".booking_change_event");
                    // ?????? ??????
                    const booking_remove_event = document.querySelector(".booking_remove_event");
                    // ????????? ?????????
                    const booking_event_title = document.querySelector(".booking_event_title");
                    // ????????? ?????? input
                    const booking_start_date = document.querySelector(".booking_start_date");
                    // ????????? ?????? input
                    const booking_end_date = document.querySelector(".booking_end_date");
                    // ????????? ??????
                    const booking_memo = document.querySelector(".booking_memo");

                    defualt_date(arg.startStr + "T00:00", arg.endStr + "T00:00");

                    // ????????? ?????????
                    booking_modal.style.display = "block";
                    // ????????? ?????? ?????? ?????????
                    booking_add_event.style.display = "block";
                    // ????????? ?????? ?????? ?????????
                    booking_change_event.style.display = "none";
                    // ????????? ?????? ?????? ?????????
                    booking_remove_event.style.display = "none";

                    // ????????? ????????? ????????????
                    booking_event_title.value = "";
                    // ????????? ????????? ?????? ??????????????? ??????
                    booking_attendees_list.style.color = "black";
                    // ????????? ?????? ???????????? ??????
                    booking_memo.value = "";
                    // ????????? ?????? ????????? ??????
                    booking_start_date.value = arg.startStr + "T00:00";
                    booking_end_date.value = arg.endStr + "T00:00";

                    booking_event_meetingroom.value = null;
                    booking_event_fixtures_box.style.height = "30px";
                    booking_event_fixtures_box.style.opacity = "1";
                    booking_event_fixtures_box.style.visibility = "visible";
                    booking_event_fixtures.value = null;
                    booking_event_meetingroom_box.style.height = "30px";
                    booking_event_meetingroom_box.style.opacity = "1";
                    booking_event_meetingroom_box.style.visibility = "visible";

                    // ????????? ????????? ??????
                    const attendees_all = document.querySelectorAll(".attendees");
                    for (let i = 0; i < attendees_all.length; i++) {
                        attendees_all[i].remove();
                    }
                    booking_attendees_list_box.childNodes[0].textContent = "???????????? ???????????? ??????????????????"

                    // ????????? ???????????? ??????
                    const user_check_box = document.querySelectorAll("input[id *= user]");
                    for (let i = 0; i < user_check_box.length; i++) {
                        user_check_box[i].checked = false;
                    }

                    // ????????? ?????? ?????? ?????? ???
                    booking_add_event.addEventListener("click", function () {
                        // ????????? ????????? ?????? ???
                        if (booking_event_title.value == "" || booking_event_title.value == null) {
                            booking_event_title.style.animation = "empty_booking_event_title 0.5s";
                            booking_event_title.classList.add("empty_booking_event_title_color");
                            setTimeout(function () {
                                booking_event_title.style.animation = "";
                            }, 300);
                            calendar.unselect();
                            return;
                            // ????????? ??? ????????? ???????????? ???????????? ?????? ???
                        } else if (booking_event_meetingroom.value == "null" && booking_event_fixtures.value == "null") {
                            booking_event_meetingroom.style.color = "red";
                            booking_event_meetingroom_box.style.animation = "empty_booking_event_title 0.5s";
                            setTimeout(function () {
                                booking_event_meetingroom_box.style.animation = "";
                                booking_event_fixtures.style.color = "red";
                                booking_event_fixtures_box.style.animation = "empty_booking_event_title 0.5s";
                                setTimeout(function () {
                                    booking_event_fixtures_box.style.animation = "";
                                }, 300)
                            }, 500);
                            calendar.unselect();
                            return;
                            // ????????? ??? ???????????? ????????? ???????????? ?????? ???
                        } else if (booking_attendees_list_box.childNodes[3] == null) {
                            booking_attendees_list.style.color = "red";
                            booking_attendees_list.style.animation = "empty_booking_event_title 0.3s";
                            setTimeout(function () {
                                booking_attendees_list.style.animation = "";
                            }, 300);
                            calendar.unselect();
                            return;
                        } else if (booking_modal.style.display == "none") {
                            calendar.unselect();
                            return;
                        }

                        booking_modal.addEventListener("click", function (event) {
                            if (event.target.classList == "booking_modal") {
                                booking_modal.style.display = "none";
                                booking_event_datetime_container.childNodes[0].data = "";
                                booking_event_datetime_box.style.height = "0px";
                                booking_event_title.classList.remove("empty_booking_event_title_color");
                                calendar.unselect();
                                location.reload()
                                return;
                            }
                        });

                        // ????????? ???????????? ????????? ????????? ????????? ??????
                        let attendees_List = new Array;
                        for (let i = 0; i < user_check_box.length; i++) {
                            if (user_check_box[i].checked == true) {
                                attendees_List.push(user_check_box[i].id.slice(4) + ",");
                            }
                        }

                        // ????????? ???????????? ???????????? ???????????? ??????
                        let attendees_emp_code_list = "";
                        for (let i = 0; i < attendees_List.length; i++) {
                            attendees_emp_code_list += attendees_List[i];
                        }

                        let obj_content = new Object();
                        obj_content.content = booking_memo.value;
                        let obj_attendees = new Object();
                        obj_attendees.attendees = attendees_emp_code_list;
                        let obj_meetingroom = new Object();
                        obj_meetingroom.meetingroom = booking_event_meetingroom.value;
                        let obj_fixtures = new Object();
                        obj_fixtures.fixtures = booking_event_fixtures.value;


                        // ????????? ?????? obj??? ??????
                        let obj = new Object();
                        obj.title = booking_event_title.value;
                        obj.start = booking_start_date.value;
                        obj.end = booking_end_date.value;
                        obj.extendedProps = obj_content;
                        obj.extendedProps = obj_attendees;
                        obj.extendedProps = obj_meetingroom;
                        obj.extendedProps = obj_fixtures;

                        let booking_event_meetingroom_code = parseInt(booking_event_meetingroom.value.slice(12));
                        let booking_event_fixtures_code = parseInt(booking_event_fixtures.value.slice(9));

                        let insert_check;
                        $.ajax({
                            url: "/booking_add",
                            method: "POST",
                            dataType: "text",
                            data: {
                                "booking_title": booking_event_title.value,
                                "booking_start_date": booking_start_date.value,
                                "booking_end_date": booking_end_date.value,
                                "booking_memo": booking_memo.value,
                                "booking_attendees": attendees_emp_code_list,
                                "meetingroom_num": booking_event_meetingroom_code,
                                "fixtures_num": booking_event_fixtures_code,
                                "emp_num": document.querySelector("#session_emp_num").value
                            },
                            success: function (data) {
                                if (data == "success") {
                                    insert_check = data;
                                    alert("????????? ?????????????????????.");
                                    calendar.addEvent({
                                        title: booking_event_title.value,
                                        start: booking_start_date.value,
                                        end: booking_end_date.value,
                                        extendedProps: {
                                            content: booking_memo.value,
                                            attendees: attendees_emp_code_list,
                                            meetingroom: booking_event_meetingroom_code,
                                            fixtures: booking_event_fixtures_code
                                        }
                                    });

                                    location.reload()
                                    return;
                                } else if (data == "error") {
                                    insert_check = data;
                                    alert("?????? ???????????? ?????? ???????????? ??????????????????.");
                                    location.reload()
                                    return;
                                }
                            }
                        })
                        return;
                    })
                },
                events: data
                // [{
                //     title: "????????????",
                //     start: "2022-09-20T00:00",
                //     end: "2022-09-30T00:00",
                //     extendedProps: {
                //         content: "?????????",
                //         attendees: "990810,931005",
                //         meetingroom: "meetingroom_1",
                //         fixtures: "null"
                //     }
                // }]
            });
            calendar.render();
        });
    });
});

// ?????????
const booking_modal = document.querySelector(".booking_modal");
// ???????????? ??????
const booking_event_datetime_container = document.querySelector(".booking_event_datetime_container");

const booking_event_datetime_box = document.querySelector(".booking_event_datetime_box");

const booking_event_container = document.querySelector(".booking_event_container");

const booking_event_title = document.querySelector(".booking_event_title");

function defualt_date(start, end) {
    // ????????? ?????? ???
    const user_start_selet_month = start.slice(5, 7);
    // ????????? ?????? ???
    const user_start_selet_day = start.slice(8, 10);
    // ????????? ?????? ??????
    const user_start_selet_hour = start.slice(11, 13);
    // ????????? ?????? ???
    const user_start_selet_min = start.slice(14, 16);


    const user_end_select_month = end.slice(5, 7);
    // ????????? ?????? ???
    const user_end_select_day = end.slice(8, 10);
    // ????????? ?????? ??????
    const user_end_select_hour = end.slice(11, 13);
    // ????????? ?????? ???
    const user_end_select_min = end.slice(14, 16);

    booking_event_datetime_container.childNodes[0].data = user_start_selet_month + "??? " + user_start_selet_day + "??? " + user_start_selet_hour + ":" + user_start_selet_min +
        " ~ " + user_end_select_month + "??? " + user_end_select_day + "??? " + user_end_select_hour + ":" + user_end_select_min;
}

booking_event_datetime_container.addEventListener("click", function () {
    booking_event_datetime_container.childNodes[0].data = "";
    booking_event_datetime_box.style.height = "50px";
});

booking_event_container.addEventListener("click", function (e) {
    if (e.target.classList != "booking_event_datetime_container" && e.target.classList != "booking_event_datetime_box" &&
        e.target.classList != "booking_start_datetime" && e.target.classList != "booking_end_datetime" &&
        e.target.classList != "booking_start_text" && e.target.classList != "booking_end_text" &&
        e.target.classList != "booking_start_date" && e.target.classList != "booking_end_date") {

        // ????????? ?????? input
        const booking_start_date = document.querySelector(".booking_start_date");
        // ????????? ?????? input
        const booking_end_date = document.querySelector(".booking_end_date");

        defualt_date(booking_start_date.value, booking_end_date.value);

        booking_event_datetime_box.style.height = "0px";
    }
})


const booking_attendees_list_box = document.querySelector(".booking_attendees_list > div");
const booking_attendees_list = document.querySelector(".booking_attendees_list");
const booking_attendees_check = document.querySelector(".booking_attendees_check");
booking_attendees_check.addEventListener("click", function (e) {
    // ??????????????? ??????????????? ??????
    if (e.target.id.indexOf("user") !== -1) {
        // ????????? ??????????????? ???????????? ????????? ??????
        if (e.target.checked == true) {
            // =============== ?????? ????????? ===============
            const attendees = document.createElement("div"); // ????????? div ??????
            const attendees_name_box = document.createElement("div"); // ?????? div ??????
            const attendees_name = document.createElement("span"); // ?????? ???????????? span ??????
            attendees.setAttribute("class", `attendees user_${e.target.id.slice(4)}`); // ????????? div??? ????????? ??????
            attendees_name.innerHTML = e.target.labels[0].innerHTML; // ?????? ???????????? div??? ????????????
            attendees_name_box.append(attendees_name); // ??????????????? ??????
            attendees.append(attendees_name_box); // ??????????????? ??????
            booking_attendees_list_box.append(attendees); // ??????????????? ??????

            booking_attendees_list_box.childNodes[0].textContent = "";

            // ????????? ??????????????? ???????????? ?????? ???????????? ?????? ??? ?????? ?????? ??????
        } else if (e.target.checked == false) {
            const user = document.querySelector(`.user_${e.target.id.slice(4)}`);
            user.remove();
            // ???????????? ???????????? ????????????
            if (booking_attendees_list_box.childNodes[1] == null) {
                booking_attendees_list_box.childNodes[0].textContent = "????????? ??? ???????????? ??????????????????"
            }
        }
    }
})

booking_modal.addEventListener("click", function (event) {
    if (event.target.classList == "booking_modal") {
        booking_event_meetingroom.value = 'null';
        booking_event_fixtures.value = 'null';
        booking_modal.style.display = "none";
        booking_event_datetime_container.childNodes[0].data = "";
        booking_event_datetime_box.style.height = "0px";
        booking_event_title.classList.remove("empty_booking_event_title_color");
    }
});

const booking_event_use_select = document.querySelector(".booking_event_use_select");

const booking_event_meetingroom_box = document.querySelector(".booking_event_meetingroom");
const booking_event_fixtures_box = document.querySelector(".booking_event_fixtures");

const booking_event_meetingroom = document.querySelector("#booking_event_meetingroom");
const booking_event_fixtures = document.querySelector("#booking_event_fixtures");

booking_event_meetingroom.addEventListener("change", function () {
    // ???????????? ???????????? ????????? ???
    if (booking_event_meetingroom.value == "null") {
        booking_event_fixtures_box.style.height = "30px";
        booking_event_fixtures_box.style.opacity = "1";
        booking_event_fixtures_box.style.visibility = "visible";
        // ???????????? ???????????? ???
    } else if (booking_event_meetingroom.value != "null") {
        booking_event_fixtures_box.style.height = "0px";
        booking_event_fixtures_box.style.opacity = "0";
        booking_event_fixtures_box.style.visibility = "hidden";
    }
});

booking_event_fixtures.addEventListener("click", function () {
    // ????????? ???????????? ????????? ???
    if (booking_event_fixtures.value == "null") {
        booking_event_meetingroom_box.style.height = "30px";
        booking_event_meetingroom_box.style.opacity = "1";
        booking_event_meetingroom_box.style.visibility = "visible";
        // ????????? ???????????? ???
    } else if (booking_event_fixtures.value != "null") {
        booking_event_meetingroom_box.style.height = "0px";
        booking_event_meetingroom_box.style.opacity = "0";
        booking_event_meetingroom_box.style.visibility = "hidden";
    }
})
// ???????????? ???????????? ?????? ?????? ????????? ?????? ?????? ???????????? ?????? ?????? ???
booking_event_fixtures.addEventListener("click", function () {
    booking_event_fixtures.style.color = "black";
    booking_event_meetingroom.style.color = "black";
})
booking_event_meetingroom.addEventListener("click", function () {
    booking_event_meetingroom.style.color = "black";
    booking_event_fixtures.style.color = "black";
})