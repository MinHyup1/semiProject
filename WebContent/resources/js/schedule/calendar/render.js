document.addEventListener('DOMContentLoaded', function() {
    //var initialLocaleCode = 'ko';
    var localeSelectorEl = document.getElementById('locale-selector');
    var calendarEl = document.getElementById('calendar');
	var prevSelected = undefined;

    var calendar = new FullCalendar.Calendar(calendarEl, {
      headerToolbar: {
        left: 'today',
        center: 'title',
        right: 'prevYear,prev,next,nextYear'
      },
      titleFormat: {
    	year: 'numeric',
    	month: 'long'
      },
      buttonText: {
    	today: 'today'  
      },
      initialDate: new Date(),
      //locale: 'ko',
      //buttonIcons: false, // show the prev/next text
      //weekNumbers: true,
      //navLinks: true, // can click day/week names to navigate views
      //editable: true,
      dayMaxEvents: true, // allow "more" link when too many events
	  
	  dateClick: function(info) {
		if(prevSelected) {
			prevSelected.style.backgroundColor = '';
		}
		/*alert('Date : ' + info.dateStr);*/
		prevSelected = info.dayEl
		info.dayEl.style.backgroundColor = '#c9d7e8';
	  },
	  

	  /* event는 등록된 일정을 의미한다. */
	  /*eventMouseEnter: function(info) {
		info.el.style.backgroundColor = 'yellow';
	  },*/

      events: [
        {
          title: 'All Day Event',
          start: '2020-09-01'
        },
        {
          title: 'Long Event',
          start: '2020-09-07',
          end: '2020-09-10'
        },
        {
          groupId: 999,
          title: 'Repeating Event',
          start: '2020-09-09T16:00:00'
        },
        {
          groupId: 999,
          title: 'Repeating Event',
          start: '2020-09-16T16:00:00'
        },
        {
          title: 'Conference',
          start: '2020-09-11',
          end: '2020-09-13'
        },
        {
          title: 'Meeting',
          start: '2020-09-12T10:30:00',
          end: '2020-09-12T12:30:00'
        },
        {
          title: 'Lunch',
          start: '2020-09-12T12:00:00'
        },
        {
          title: 'Meeting',
          start: '2020-09-12T14:30:00'
        },
        {
          title: 'Happy Hour',
          start: '2020-09-12T17:30:00'
        },
        {
          title: 'Dinner',
          start: '2020-09-12T20:00:00'
        },
        {
          title: 'Birthday Party',
          start: '2020-09-13T07:00:00'
        },
        {
          title: 'Click for Google',
          url: 'http://google.com/',
          start: '2020-09-28'
        }
      ]
    });

    calendar.render();

    /* // build the locale selector's options
    calendar.getAvailableLocaleCodes().forEach(function(localeCode) {
      var optionEl = document.createElement('option');
      optionEl.value = localeCode;
      optionEl.selected = localeCode == initialLocaleCode;
      optionEl.innerText = localeCode;
      localeSelectorEl.appendChild(optionEl);
    });

    // when the selected option changes, dynamically change the calendar option
    localeSelectorEl.addEventListener('change', function() {
      if (this.value) {
        calendar.setOption('locale', this.value);
      }
    }); */
	
	document.querySelector('.fc-scrollgrid-sync-table>tbody').addEventListener('dblclick', e => {
		/* 일정등록 선택 화면 뿌리기 */
		document.querySelector('.select_menu').style.display = 'initial';
		
		document.querySelector('.select_menu').addEventListener('click', e => {
			if(e.target.className == 'top_menu' || e.target.className == 'bottom_menu'){
				document.querySelector('.select_menu').style.display = 'none';
			};
		});
		
	});
	

});