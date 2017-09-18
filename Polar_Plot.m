close all
clear all
clc
m = 8;
signal=(sin(20* pi/180));
ad = exp (-1j * pi *[0: m-1]'*signal);% array response vector in the direction of desired signal. expect the direction of the array response vector
wop = ad;
thetas = [-90:90];
tm = thetas * pi/180;
am = exp (-1j * pi * [0: m-1]'* (sin (tm)));
A = abs (wop'* am);% array response array response
A = A / max (A);
figure, polar (tm, A)
A = 10 * log10 (A);% log figure log plot
title ('Normalized magnitude response array polar diagram, eight array elements')
figure, plot (thetas, A);
title ('eight microphones');
xlabel ('angle[degrees]');
ylabel ('Normalized Beam Power[dB]');
grid on