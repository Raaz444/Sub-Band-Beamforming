#include <processor_include.h>
#include <sysreg.h>
#include <signal.h>
#include <string.h>
#include <filter.h>
#include <stdio.h>
#include <stdlib.h>

#include "framework.h"

static int ledcounter;

void process(int sig)
{
    int n;
    sample_t bak;
 
    // Get a pointer to the current audio block.
    sample_t *audioin  = dsp_get_audio();
    sample_t *audioout = dsp_get_audio();
    
    // Copy audio from input to output.
    for(n=0; n<DSP_BLOCK_SIZE; ++n)
    {
    	bak = audioin[n];
        audioout[n].left = bak.right;
        audioout[n].right = bak.left;
    }
}

static void keyboard(int sig)
{
    unsigned int keys = dsp_get_keys();
    
    if(keys & SW1)
    {
        dsp_set_leds(0);
    }
    
    else if(keys & SW2) 
    {
        dsp_set_leds(LED1);
    }
    
    else if(keys & SW3) 
    {
        dsp_set_leds(LED2);
    }
    
    else if(keys & SW4) 
    {
        dsp_set_leds(LED1 | LED2);
    }
}

static void timer(int sig)
{  
    ledcounter = (ledcounter+1) & 0x3F;
    //dsp_set_leds(ledcounter);
}

float myfunction(float inval)
{
	return 2.0*inval;
}

void main()
{   
    // Setup the DSP framework
    dsp_init();
 
    // Register interrupt handlers:
    // SIG_SP1: the audio callback
    // SIG_USR0: the keyboard callback
    // SIG_TMZ: the timer callback
    interrupt(SIG_SP1, process);
    interrupt(SIG_USR0, keyboard);
    interrupt(SIG_TMZ, timer);
    timer_set(9830400, 9830400);
    timer_on();
    
    // initiering_student_kod();
    
    // Enable the DSP framework.
    dsp_start();
    
    
    
    // Everything is handled by the interrupt handlers, so just put an empty
    // idle-loop here. If not, the program falls back to an equivalent idle-loop
    // in the run-time library when main() returns.
    for(;;)
    {
        idle();
    }
}

