import 'package:flutter/material.dart';

class MoviesPage extends StatelessWidget {
  const MoviesPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('CineVault')),
      body: const Column(
        children: [Text('My Movies'), Text('All | Want to Watch | Watched')],
      ),
    );
  }
}
